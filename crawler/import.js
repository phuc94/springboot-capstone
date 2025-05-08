import fs from 'fs'
import path from 'path'
import axios from 'axios'

const FOLDER = './craw_data';
const LOG_FILE = './log.txt';
const API_URL = 'http://your-server.com/api/import';
const HEADERS = { 'Content-Type': 'application/json' };

async function main () {
  const files = readFilesFromDir(FOLDER)
  
  for (const file of files) {
    const content = getFileContent(file)

    callApi(file, content)
  }

}

async function callAPI(file, content) {
    try {
      const res = await axios.post(API_URL, { content }, { headers: HEADERS });
      console.log(`${file} -> ${res.status}`);
      logToFile(`${file}: ${res.status} ${res.statusText}`);
    } catch (err) {
      console.error(`${file} -> ERROR:`, err.message);
      logToFile(`${file}: ERROR ${err.message}`);
    }
}

function readFilesFromDir(dir) {
  return fs.readdirSync(FOLDER).filter(f => f.endsWith('.txt'));
}

function getFileContent(file) {
    const filePath = path.join(FOLDER, file);
    return fs.readFileSync(filePath, 'utf8');
}

function logToFile(message) {
  fs.appendFileSync(LOG_FILE, message + '\n');
}

main()
