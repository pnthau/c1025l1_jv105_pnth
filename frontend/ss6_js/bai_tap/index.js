// let a = 10, b = 20, c = 30;
// console.log(a, b, c);
// // 👉 In ra: 10 20 30
// // Đây là cú pháp dấu phẩy trong khai báo biến hoặc trong console.log,
// // không phải toán tử phẩy.

let y = (a = 5, a + 10); // a duoc goi la non strict
console.log(y);
// 👉 Kết quả: 15
// Toán tử phẩy: thực hiện a = 5 (bỏ), rồi a + 10 (giữ lại).
