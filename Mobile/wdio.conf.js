exports.config = {
  services: ['appium'],
  port: 4723,

  // PATH is important for appium local server :
  path: '/wd/hub/',

  runner: 'local',
  specs: ['./tests/*.js'],
  capabilities: [
    {
      maxInstances: 1,
      appiumVersion: '1.22.0',
      platformName: 'Android',
      platformVersion: '11',
      deviceName: 'Pixel',
      appPackage: 'com.tumblr',
      appActivity: 'com.tumblr.ui.activity.JumpoffActivity',
      automationName: 'UiAutomator2',
    },
  ],

  logLevel: 'trace',
  bail: 0,
  waitforTimeout: 10000,
  connectionRetryTimeout: 90000,
  connectionRetryCount: 3,
  framework: 'mocha',
  reporters: ['spec'],
  mochaOpts: {
    ui: 'bdd',
    timeout: 60000,
  },
};