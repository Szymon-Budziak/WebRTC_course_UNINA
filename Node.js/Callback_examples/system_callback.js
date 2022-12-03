const fs = require('fs');

console.log("Before readFile");
fs.readFile('myFile.txt', 'utf8', (err, data) => {
    console.log("I am the callback, here is the read data: ");
    console.log(data);
})
console.log("After readFile");
