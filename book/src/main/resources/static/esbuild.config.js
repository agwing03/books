const postCssPlugin = require("@deanc/esbuild-plugin-postcss");
const chokidar = require("chokidar");
const esbuild = require("esbuild");
const copy = require("copy");
const path = require("path");
const tailwindConfig = require("./tailwind.config");

/* 
*************************************************************************
# DEFINE SOURCE AND DISTRIBUTION PATHS
*************************************************************************
*/
const sourcePath = "src/";
const distPath = "dist/";

/* 
*************************************************************************
# SPECIFY FILES AND DIRECTORIES TO WATCH
*************************************************************************
*/
const copyDirs = ["images", "json"];

/* 
*************************************************************************
# SPECIFY ENTRIES FOR JS AND CSS FILES
*************************************************************************
*/
const entries = [
  // CSS Vendors
  "css/vendors/ckeditor.css",
  "css/vendors/dropzone.css",
  "css/vendors/full-calendar.css",
  "css/vendors/highlight.css",
  "css/vendors/leaflet.css",
  "css/vendors/litepicker.css",
  "css/vendors/simplebar.css",
  "css/vendors/tabulator.css",
  "css/vendors/tiny-slider.css",
  "css/vendors/tippy.css",
  "css/vendors/toastify.css",
  "css/vendors/tom-select.css",
  "css/vendors/zoom-vanilla.css",

  // CSS Themes
  "css/themes/enigma/side-nav.css",
  "css/themes/enigma/top-nav.css",
  "css/themes/icewall/side-nav.css",
  "css/themes/icewall/top-nav.css",
  "css/themes/rubick/side-nav.css",
  "css/themes/rubick/top-nav.css",
  "css/themes/tinker/side-nav.css",
  "css/themes/tinker/top-nav.css",

  // CSS Components
  "css/components/mobile-menu.css",

  // CSS General
  "css/app.css",

  // JS Vendor
  "js/vendors/accordion.js",
  "js/vendors/alert.js",
  "js/vendors/axios.js",
  "js/vendors/calendar/calendar.js",
  "js/vendors/calendar/plugins/day-grid.js",
  "js/vendors/calendar/plugins/interaction.js",
  "js/vendors/calendar/plugins/list.js",
  "js/vendors/calendar/plugins/time-grid.js",
  "js/vendors/chartjs.js",
  "js/vendors/dayjs.js",
  "js/vendors/ckeditor/balloon.js",
  "js/vendors/ckeditor/balloon-block.js",
  "js/vendors/ckeditor/classic.js",
  "js/vendors/ckeditor/document.js",
  "js/vendors/ckeditor/inline.js",
  "js/vendors/popper.js",
  "js/vendors/dom.js",
  "js/vendors/dropdown.js",
  "js/vendors/dropzone.js",
  "js/vendors/highlight.js",
  "js/vendors/image-zoom.js",
  "js/vendors/leaflet-map.js",
  "js/vendors/litepicker.js",
  "js/vendors/lodash.js",
  "js/vendors/lucide.js",
  "js/vendors/modal.js",
  "js/vendors/pristine.js",
  "js/vendors/simplebar.js",
  "js/vendors/svg-loader.js",
  "js/vendors/tab.js",
  "js/vendors/tabulator.js",
  "js/vendors/tailwind-merge.js",
  "js/vendors/tiny-slider.js",
  "js/vendors/tippy.js",
  "js/vendors/toastify.js",
  "js/vendors/tom-select.js",
  "js/vendors/transition.js",
  "js/vendors/xlsx.js",

  // JS Utils
  "js/utils/colors.js",
  "js/utils/helper.js",

  // JS Pages
  "js/pages/chat.js",
  "js/pages/modal.js",
  "js/pages/notification.js",
  "js/pages/slideover.js",
  "js/pages/tabulator.js",
  "js/pages/validation.js",

  // JS Themes
  "js/themes/rubick.js",
  "js/themes/icewall.js",
  "js/themes/tinker.js",
  "js/themes/enigma.js",

  // JS Base Components
  "js/components/base/theme-color.js",
  "js/components/base/calendar/calendar.js",
  "js/components/base/calendar/draggable.js",
  "js/components/base/balloon-block-editor.js",
  "js/components/base/balloon-editor.js",
  "js/components/base/classic-editor.js",
  "js/components/base/document-editor.js",
  "js/components/base/dropzone.js",
  "js/components/base/highlight.js",
  "js/components/base/inline-editor.js",
  "js/components/base/leaflet-map-loader.js",
  "js/components/base/litepicker.js",
  "js/components/base/lucide.js",
  "js/components/base/preview-component.js",
  "js/components/base/source.js",
  "js/components/base/tiny-slider.js",
  "js/components/base/tippy.js",
  "js/components/base/tippy-content.js",
  "js/components/base/tom-select.js",

  // JS Components
  "js/components/themes/enigma/top-bar.js",
  "js/components/themes/icewall/top-bar.js",
  "js/components/themes/rubick/top-bar.js",
  "js/components/themes/tinker/top-bar.js",
  "js/components/donut-chart.js",
  "js/components/horizontal-bar-chart.js",
  "js/components/line-chart.js",
  "js/components/mobile-menu.js",
  "js/components/pie-chart.js",
  "js/components/report-bar-chart-1.js",
  "js/components/report-bar-chart.js",
  "js/components/report-donut-chart-1.js",
  "js/components/report-donut-chart-2.js",
  "js/components/report-donut-chart.js",
  "js/components/report-line-chart.js",
  "js/components/report-pie-chart.js",
  "js/components/simple-line-chart-1.js",
  "js/components/simple-line-chart-2.js",
  "js/components/simple-line-chart-3.js",
  "js/components/simple-line-chart-4.js",
  "js/components/simple-line-chart.js",
  "js/components/stacked-bar-chart-1.js",
  "js/components/stacked-bar-chart.js",
  "js/components/vertical-bar-chart.js",
];

(async () => {
  const watchMode = process.argv.includes("--watch");
  const formattedEntries = entries.map((entry) => `${sourcePath}${entry}`);

  const loadingState = {
    isRun: true,
    currentIndex: 0,
    mapper: [
      " □ □ □ □ □ □ □ □ □ □ ",
      " ■ □ □ □ □ □ □ □ □ □ ",
      " ■ ■ □ □ □ □ □ □ □ □ ",
      " ■ ■ ■ □ □ □ □ □ □ □ ",
      " ■ ■ ■ ■ □ □ □ □ □ □ ",
      " ■ ■ ■ ■ ■ □ □ □ □ □ ",
      " ■ ■ ■ ■ ■ ■ □ □ □ □ ",
      " ■ ■ ■ ■ ■ ■ ■ □ □ □ ",
      " ■ ■ ■ ■ ■ ■ ■ ■ □ □ ",
      " ■ ■ ■ ■ ■ ■ ■ ■ ■ □ ",
      " ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ ",
    ],
    title: null,
    info: null,
  };

  const animate = setInterval(() => {
    console.clear();
    console.log(
      "--------------------------------------------------------------------"
    );
    if (loadingState.title !== null) {
      console.log(
        `| ${loadingState.title.toUpperCase()}${new Array(
          68 - loadingState.title.length - 24
        )
          .fill(" ")
          .join("")}${
          loadingState.isRun
            ? loadingState.mapper[loadingState.currentIndex]
            : new Array(21).fill(" ").join("")
        }|`
      );
    }
    console.log(
      "--------------------------------------------------------------------"
    );
    loadingState.info !== null && console.log(loadingState.info);

    loadingState.currentIndex++;

    if (loadingState.currentIndex == 11) {
      loadingState.currentIndex = 0;
    }
  }, 100);

  const build = async (entry, config = {}) =>
    await esbuild.build({
      entryPoints: [entry],
      bundle: true,
      external: ["*.png"],
      plugins: [postCssPlugin(require("./postcss.config"))],
      outfile: entry.replaceAll(sourcePath, distPath),
      alias: {
        "tailwind-config": path.resolve("./tailwind.config.js"),
      },
      ...config,
    });

  copyDirs.map((dir) => {
    copy(`${sourcePath}${dir}/**/*.*`, `${distPath}${dir}`, () => {});
  });

  loadingState.title = "Compiling, please wait...";

  await Promise.all(
    formattedEntries.map(async (entryPoint) => {
      await build(entryPoint, {
        minify: !watchMode,
      });

      loadingState.info = entryPoint;
    })
  );

  if (watchMode) {
    loadingState.title = "Waiting for changes to files...";
    loadingState.info = null;
    loadingState.isRun = false;

    chokidar.watch(formattedEntries).on("change", async (path) => {
      await build(path);
    });

    chokidar
      .watch(tailwindConfig.content, {
        ignoreInitial: true,
      })
      .on("all", async () => {
        loadingState.isRun = true;
        loadingState.title = "Compiling, please wait...";

        await Promise.all(
          formattedEntries
            .filter((entryPoint) => entryPoint.split(".").slice(-1)[0] == "css")
            .map(async (entryPoint) => {
              await build(entryPoint);
              loadingState.info = entryPoint;
            })
        );

        loadingState.title = "Waiting for changes to files...";
        loadingState.info = null;
        loadingState.isRun = false;
      });
  } else {
    loadingState.title = "Compilation process completed.";
    loadingState.info = null;
    loadingState.isRun = false;

    setTimeout(() => {
      clearInterval(animate);
    }, 1000);
  }
})();
