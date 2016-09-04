class Book {

    constructor(private _title: string,
                private _author: string,
                private _available: boolean,
                private _category: Category) {

    }

    get title(): string {
        return this._title;
    }

    set title(value: string) {
        this._title = value;
    }

    get author(): string {
        return this._author;
    }

    set author(value: string) {
        this._author = value;
    }

    get available(): boolean {
        return this._available;
    }

    set available(value: boolean) {
        this._available = value;
    }

    get category(): Category {
        return this._category;
    }

    set category(value: Category) {
        this._category = value;
    }
}

function getAllBooks(): Book[] {

    return [
        new Book('Na Drini cuprija', 'Ivo Andric', true, Category.Poetry),
        new Book('Ana Karenjina', 'Lav Tolstoj', false, Category.Biography),
        new Book('Dervis i smrt', 'Mesa Selimovic', false, Category.Fiction)
    ];
}

function logFirstAvailable(books): void {

    let numberOfBooks: number = books.length;
    let firstAvailable: string = '';

    for (let currentBook of books) {


        if (currentBook.available) {
            firstAvailable = currentBook.title;
            break;
        }
    }

    console.log('Total books: ' + numberOfBooks);
    console.log('First available: ' + firstAvailable);
}

enum Category { Biography, Poetry, Fiction, Children}
;

function getBookTitlesByCategory(categoryFilter: Category): Array<string> {

    console.log("Getting books in category: " + Category[categoryFilter]);

    const allBooks: Array<Book> = getAllBooks();
    const filteredTitles: string [] = [];

    for (let currBook of allBooks) {
        if (currBook.category === categoryFilter) {
            filteredTitles.push(currBook.title);
        }
    }

    return filteredTitles;
}

function logBookTitles(titles: string[]): void {
    for (let title of titles) {
        console.log(title);
    }
}

const poetryBooks = getBookTitlesByCategory(Category.Poetry);
logBookTitles(poetryBooks);


// const allBooks = getAllBooks();
// allBooks.push(new Book('Zlocin i kazna', 'Fjodor Dostojevski', true, Category.Biography));
// logFirstAvailable(allBooks);