enum Category1 { Biography, Poetry, Fiction}; // 0, 1, 2

enum Category2 { Biography = 1, Poetry, Fiction}; // 1, 2, 3

enum Category3 { Biography = 5, Poetry = 8, Fiction = 9}; // 5, 8, 9


let favCategory: Category1 = Category1.Poetry; // number

console.log(favCategory);

let catString = Category1[favCategory]; // string

console.log(catString);