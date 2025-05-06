import puppeteer from 'puppeteer';
import fs from 'fs'

const siteUrl = 'https://muaga.me/steam/'
const elementQuerySelector = 'a.woocommerce-LoopProduct-link.woocommerce-loop-product__link';
const dataFolderName = 'craw_data'

async function main() {
  const {browser, page} = await init(siteUrl)

  const elements = await queryElementsWithSelector(elementQuerySelector, page)

  const urlArray = await getElementHref(elements, page)

  //TODO: do batch works with 5 url/batch

  await Promise.all(urlArray.slice(0,1).map((url) => getGameDescription(browser, url)))

  browser.close();
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
  await page.setViewport({width: 1080, height: 1024});
  await page.goto(url);

  const textContent = await page?.evaluate(() =>{
    return document.querySelector('#tab-description').innerHTML;
  })
  writeToFile(textContent, url)
  await page.close()
}

function writeToFile(string, url) {
  const formatedUrl = url.match(/\/([^\/]+)\/$/)[1];
  // clean up old file
  if (fs.existsSync(`${dataFolderName}/${formatedUrl}.txt`)) {
    fs.unlink(`${dataFolderName}/${formatedUrl}.txt`, function (err) {
      if (err) throw err;
      console.log(`[DELETED]: ${formatedUrl}.txt`);
    });
  }
  // create & write to file
  fs.appendFile(`${dataFolderName}/${formatedUrl}.txt`, string.trim(), function (err) {
    if (err) throw err;
    console.log(`[SAVED]  : ${formatedUrl}.txt`);
  });
}

await main()

