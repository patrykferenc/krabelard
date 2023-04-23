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

Starts all services locally, in dev mode.

Dev mode communicates via localhost, and allows communication from the host.
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
    echo "Starting backend in dev environment..."

    echo "Building local packages..."

    (echo "Packaging krapi..." && cd krapi && ./docker/local_package.sh)
    (echo "Packaging timetables..." && cd timetables && ./docker/local_package.sh)
    (echo "Packaging vehicle-positions..." && cd vehicle-positions && ./docker/local_package.sh)

    echo "Starting docker compose..."
    docker compose up --build
}

main "$@"