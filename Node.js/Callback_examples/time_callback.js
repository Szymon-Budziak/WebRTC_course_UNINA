console.log("Before setTimeout");
setTimeout(() => {
    console.log("I am the callback!");
}, 0);
console.log("After setTimeout");