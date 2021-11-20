class DashBoard {
    get accountIcon () {
        const selector = 'com.tumblr:id/topnav_account_button_img_active'
        return $(`android=resourceId("${selector}")`)
    }

    async pick5tags () {
        // const selector = 'com.tumblr:id/category_list'
        // const scroll = $(`android=resourceId("${selector}")`)
        // const indexer = (num) => { return $(`android=fromParent(new UiSelector().resourceId("${selector}")).index(${num})`) }
        // const indexer = async (num) => { return await $(`android=resourceId("${selector}")`).$$('index', num).touchClick }
        // await indexer(1).touchClick()
        // await driver.touchClick(indexer(4))
        // await driver.touchClick(indexer(7))
        // await driver.touchClick(indexer(10))
        // await driver.touchClick(indexer(13))
    }

    checkIfExist (str) {
        return $(`android=textContains("${str}")`)
    }
}

export default new DashBoard()
