#!/usr/bin/env bash

set -o errexit
set -o nounset
set -o pipefail

if [[ "${TRACE-0}" == "1" ]]; then
    set -o xtrace
fi

cd "$(dirname "$0")"

usage() {
  echo "
Usage: $0

Builds zip file to simulate local pipeline.
  "
  exit 2
}

while getopts "h?" opt; do
    case $opt in
        h|*)
            usage
            ;;
    esac
done

shift $((OPTIND-1))

main() {
      echo "Preparing a jar file to simulate pipeline packaging..."
      rm -rf b
      mkdir b

      (cd .. && ./gradlew clean bootJar)

      cp -vR ../build/libs/krapi.jar b/

      zip -r build.zip b/ -x "*.DS_Store"
}

main "$@"
