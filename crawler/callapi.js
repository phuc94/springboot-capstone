import fs from 'fs'
import path from 'path'
const separator = ':,:'

function readAllTxtFiles(directoryPath) {
    const files = [];

    try {
        // Read directory contents synchronously
        const dirContents = fs.readdirSync(directoryPath);

        // Filter for .txt files only
        const txtFiles = dirContents.filter(file =>
            path.extname(file).toLowerCase() === '.txt'
        );

        // Read each txt file synchronously
        txtFiles.forEach(filename => {
            const filePath = path.join(directoryPath, filename);

            try {
                const content = fs.readFileSync(filePath, 'utf8');
                files.push({
                    filename: filename,
                    content: content,
                    fullPath: filePath
                });

            } catch (err) {
                console.error(`Error reading file ${filename}:`, err.message);
            }
        });

        return files;

    } catch (err) {
        console.error(`Error reading directory ${directoryPath}:`, err.message);
        return [];
    }
}


// Usage example
function processFiles() {
    const txtFiles = readAllTxtFiles('./craw_data');

    if (txtFiles.length === 0) {
        console.log('No .txt files found or directory not accessible');
        return;
    }

    // Process each file
    txtFiles.forEach((file, index) => {
        const title = file.filename
            .replace('.txt', '')
            .replace(/-/g, ' ')
            .replace(/\b\w/g, char => char.toUpperCase());
        let [media, price, description] = file.content.split(separator);
        price = price.replace('.', '');
        fetch('http://phucserver:8080/admin/game', {
            method: 'POST',
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title,
                price,
                description,
                media,
                platformId: 2,
                stock: 50
            }),
        })
        .then(response => {
          if (!response.ok && response.status != 200) {
            throw new Error(`HTTP error! status: ${response.status}`);
          }
          return response.json();
        })
    });
}

// Alternative function if you need just the data without console output
function getTxtFilesData(directoryPath = '/data') {
    const files = [];

    try {
        const dirContents = fs.readdirSync(directoryPath);
        const txtFiles = dirContents.filter(file => 
            path.extname(file).toLowerCase() === '.txt'
        );

        txtFiles.forEach(filename => {
            const filePath = path.join(directoryPath, filename);
            try {
                const content = fs.readFileSync(filePath, 'utf8');
                files.push({ filename, content });
            } catch (err) {
                // Silently skip files that can't be read
                console.warn(`Skipping ${filename}: ${err.message}`);
            }
        });

    } catch (err) {
        console.error(`Directory error: ${err.message}`);
    }

    return files;
}

function main(){
  processFiles()
}
main()
