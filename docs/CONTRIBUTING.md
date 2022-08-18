# Contributing

Please adhere to the guidelines described in the document.

## Git workflow

### General

1. Do not code anything without creating an associated issue.
2. Always check if an issue you are trying to address is not already created/in scope of a different issue.
3. Always use PRs when merging feature or bug branches into the master branch.
4. It's recommended to do the same with wip branches that will be merged into feature/bug branches.

### Branches

Please use the following structure:

 - master 
   - that's the main branch, if you create a feature/bug branch please base it from here
   - never merge into it directly, always use PRs
 - feature/123-Add_some_functionality
   - these are feature branches - for when you add a new functionality
   - describe what you are adding
   - always reference the issue
   - make sure the issue is correctly labeled
 - bugs/123-Fix_broken_button
   - use bugs/ when you are fixing something
   - if a bug concerns an active feature branch, you can create them from the feature branch, use master otherwise
 - wip/222-Add_tests_to_a_feature
   - work in progress branches are useful and can be used especially when splitting a feature into smaller chunks
   - do not create PRs from wip branches into master
   - it is recommended to reference the issue number if possible

### Commits

- Use present tense.
- Example commit:
  - \#123: Add bus test fixtures.
- Reference the issue number.

### Pull requests and code review

- All merges are done using PRs.
- Reference the work you do in the PR.
- Make sure the tests are passing if you want your changes to be merged.
- Resolve all comments under your PR.
- Always make sure that at least one developer accepts your changes - ideally all main devs.
- Don't get upset over comments :>

Default merge strategy:
- merge using rebase
- squash commits when merging

## Code style

We use prettier in our project [tbd], always run [tbd: command to run prettier].

We try to stick (at least a bit) to the [Google guidelines](https://google.github.io/styleguide/javaguide.html).

If package names are getting long split them using dots (bad example but prefer bus.lines over buslines etc.)

## Tests

Rely on unit tests and use integration tests only when applicable (most of your tests should be unit tests).
Add tests if you add a feature. Use the //given //when //then convention. Be very declarative what you do.

For example:

shouldThrowLineNotFoundException_whenRetrievingScheduleForLineThatDoesNotExist()

and not:

testBusErrorThrow()