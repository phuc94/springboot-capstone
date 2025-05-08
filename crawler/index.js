import puppeteer from 'puppeteer';
import fs from 'fs'

const siteUrl = 'https://muaga.me/steam/'
const elementQuerySelector = 'a.woocommerce-LoopProduct-link.woocommerce-loop-product__link';
const dataFolderName = 'craw_data'
const separator = '\n'

async function main() {
  let {browser, page} = await init(siteUrl)

  while (true) {
    const elements = await queryElementsWithSelector(elementQuerySelector, page)

    const urlArray = await getElementHref(elements, page)
    const roundedUrlArrayLength = ((urlArray.length % 5) === 0) ? urlArray.length : Math.floor(urlArray.length/5)*5 + 5

    for (let i = 0; (i*5) <= roundedUrlArrayLength; i++) {
      await Promise.all(urlArray.slice(i*5,(i+1)*5).map((url) => getGameDescription(browser, url)))
    }

    await getGameDescription(browser, 'https://muaga.me/steam/steam-key/euro-truck-simulator-2-steam-key/')
  
    // page = await goNextPage(page)
  }
  

  browser.close();
}

async function goNextPage(page) {
  const currentPageEl = await page.locator('li > span.page-number.current').waitHandle()
  await currentPageEl?.evaluate(el => {
    el.parentElement.nextSibling.children[0].click()
  })
  await page.waitForNavigation()
  // TODO: break when reach last page
  return page
}

async function queryElementsWithSelector(selector, page) {
  return await page.$$(selector);
}
async function getElementHref(elements, page) {
  return await Promise.all(elements.map(async(element) => {
    const url = await page.evaluate(el => el.href, element);
    return url
  }))
}

async function init(url) {
  const browser = await puppeteer.launch({headless: false});
  const page = await browser.newPage();
  await page.goto(url);
  await page.setViewport({width: 1080, height: 1024});
  return {browser, page}
}

async function getGameDescription(browser, url) {
  if (url === false) return
  const page = await browser.newPage();
  await page.setViewport({width: 1080, height: 2048});
  await page.goto(url);

  await page.waitForSelector('img.entered.lazyloaded')

  const imgSrc = await getThumbImg(page);
  const price = await getPrice(page);
  let textContent = await page?.evaluate(() =>{
    return document.querySelector('#tab-description').innerHTML;
  })

  const textToWrite = imgSrc + separator +
    price.replace('₫', '') + separator +
    textContent.trim().replace(/"/g, '\\"').replace(/\n/g, '\\n')

  writeToFile(textToWrite, url)
  console.log(url)
  await page.close()
}

async function getThumbImg(page) {
  const imgEl = await page
    .locator('picture.wp-post-image.ux-skip-lazy img')
    .waitHandle()
  const imgSrc = await imgEl?.evaluate(el=>el.src)
  return imgSrc
}

async function getPrice(page) {
  const priceEl = await page
    .locator('p.price.product-page-price span.woocommerce-Price-amount.amount > bdi')
    .waitHandle()
  const price = await priceEl?.evaluate(el=>el.textContent)
  return price
}

function writeToFile(string, url) {
  const formatedUrl = url.match(/\/([^\/]+)\/$/)[1];
  // clean up old file
  if (fs.existsSync(`${dataFolderName}/${formatedUrl}.txt`)) {
    fs.unlinkSync(`${dataFolderName}/${formatedUrl}.txt`);
  }
  // create & write to file
  fs.appendFileSync(`${dataFolderName}/${formatedUrl}.txt`, string);
}

await main()

