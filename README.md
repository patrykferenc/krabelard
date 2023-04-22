# krabelard

## Automatic formatting

### Prettier

Code is formatted using prettier so make sure you first install node (use the recommended version from .nvmrc file).
It is also recommended that you install the Prettier plugin in IntelliJ.

Setup: 0. It is recommended to use nvm (install it via brew/pacman/apt/clone it from GitHub).

1. Install and switch to the required node version `nvm use`.
2. Install all required dependencies `npm install .`.
3. Run prettier `npm run format`.

### IntelliJ plugin

1. Download the plugin from the marketplace "Prettier"
2. Make sure it's enabled
3. Go to Language & Frameworks -> Javascript -> Prettier, select it from the dropdown menu and click both:
   1. on reformat
   2. reformat on save
4. Go to Editor -> Code Style -> Javascript -> Prettier and select the prettier file from the project root (node_modules)
5. IntelliJ plugin does not run the format via the npm script so you need to add all the files manually (make sure that java js etc. are selected)
