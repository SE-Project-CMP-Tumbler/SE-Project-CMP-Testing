exports.config = {
    port: 4723,
    path: '/wd/hub/',

    runner: 'local',
    specs: [
        './test/*.js'
    ],
    // Patterns to exclude.
    exclude: [
        // 'path/to/excluded/files'
    ],
    maxInstances: 1,
    capabilities: [{
        maxInstances: 1,
        platformName: 'Android',
        'appium:platformVersion': '11',
        'appium:avd': 'Pixel',
        'appium:appPackage': 'com.tumblr',
        'appium:appActivity': 'com.tumblr.ui.activity.JumpoffActivity',
        'appium:automationName': 'UiAutomator2',
        'appium:udid': 'emulator-5554',
        'appium:skipDeviceInitialization': true,
        'appium:skipServerInstallation': true,
        'appium:dontStopAppOnReset': true
    }],
    logLevel: 'info',
    bail: 0,
    baseUrl: 'http://localhost',
    waitforTimeout: 10000,
    connectionRetryTimeout: 120000,
    connectionRetryCount: 3,
    framework: 'mocha',
    reporters: [
        ['spec', {
            symbols: {
                passed: '[PASS]',
                failed: '[FAIL]'
            },
            addConsoleLogs: true
        }]
    ],

    mochaOpts: {
        ui: 'bdd',
        timeout: 120000
    }
}
