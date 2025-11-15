
//## Bai 1
let i = 10;
let f = 20.5;
let b = true;
let s = 'Hà nội';

document.writeln('Bài 1: ');
document.writeln('<br>');
document.writeln('i = ' + i);
document.writeln('<br>');
document.writeln('f = ' + f);
document.writeln('<br>');
document.writeln('b = ' + b);
document.writeln('<br>');
document.writeln('s = ' + s);
document.writeln('<br>');
document.writeln('<br>');
//## Bai 2

let width = 20;
let height = 10;
let area = width * height;

document.writeln('Bài 2: ');
document.writeln('<br>');
document.writeln("Area =" + area);
document.writeln('<br>');

// ##Bai 3
let numA = +prompt("Enter number A:");
let numB = +prompt("Enter number B:");

let multiple = numA % numB;
if (multiple == 0) {
    alert("a is a multiple of b");
}
else {
    alert('a is not a multiple of b');
}