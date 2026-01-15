// function reverse(str) {
//     if (str.length <= 0) {
//         return;
//     }
//     console.log(str[str.length - 1]);
//     reverse(str.substring(0, str.length - 1));
// }

// String.prototype.reverse = function () {
//     const START_STRING = 0;
//     if (this.length <= 1) {
//         return this;
//     }
//     return this[this.length - 1] + this.substring(0, this.length - 1).reverse();
// };

// "hello".reverse();

// /**
//  * @param {Int32Array[]} array - Đây là một mảng các chuỗi
//  */
/**
 * 
 * @param {Int32List[]} array 
 * @returns 
 */
// function isSorted(array) {
//     for (let i = 1; i < array.length; i++) {
//         if (array[i] < array[i - 1]) {
//             return false;
//         }
//     }
//     return isSorted(array.slice(0, array.length - 1));
// }


// isSorted([1, 2, 8, 3, 20]);

/**
 * 
 * @param {Int16Array} n 
 * @param {Int16Array[]} source 
 * @param {Int16Array[]} target 
 * @param {Int16Array[]} auxiliary 
 */
// function hanoiTower(n, source, target, auxiliary) {
//     if (n === 1) {
//         target.push(source.pop());
//         return;
//     }
//     hanoiTower(n - 1, source, auxiliary, target);
//     target.push(source.pop());
//     hanoiTower(n - 1, auxiliary, target, source);
// }

// hanoiTower(3, [3, 2, 1], [], []);

function printBinaryString(n, bit = "") {
    if (n < 1) {
        console.log(bit);
        return;
    }
    printBinaryString(n - 1, bit + "0");
    printBinaryString(n - 1, bit + "1");
}

printBinaryString(3, "");