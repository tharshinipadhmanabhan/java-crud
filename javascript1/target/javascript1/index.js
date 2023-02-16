var books = [];
var updateIndex = 0;
function getAll() {
    alert();
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            books = JSON.parse(this.responseText);
            console.log(books);
            _displayItems(books);
        }
    };
    xhttp.open("GET", "http://localhost:9090/books");
    xhttp.send();
}

function _displayItems(data) {
    const tBody = document.getElementById("books");
    const button = document.createElement("button");
    data.forEach((item) => {
        let editButton = button.cloneNode(false);
        editButton.innerText = "Edit";
        editButton.setAttribute("onclick", `editItem(${item.id})`);
        let delButton = button.cloneNode(false);
        delButton.innerText = "Delete";
        delButton.setAttribute("onclick", `deleteItem(${item.id})`);
        console.log(item.title);
        let tr = tBody.insertRow();
        let td1 = tr.insertCell(0);
        let id = document.createTextNode(item.id);
        td1.appendChild(id);
        let td2 = tr.insertCell(1);
        let title = document.createTextNode(item.title);
        td2.appendChild(title);
        let td3 = tr.insertCell(2);
        let author = document.createTextNode(item.author);
        td3.appendChild(author);
        let td4 = tr.insertCell(3);
        let price = document.createTextNode(item.price);
        td4.appendChild(price);
        let td5 = tr.insertCell(4);
        td5.appendChild(editButton);
        let td6 = tr.insertCell(5);
        td6.appendChild(delButton);
    });
}
function saveORupdateItem() {
    //   document.getElementById("myBtn").innerHTML == "Save"
    //     ? addItem()
    //     : updateItem();
    if (document.getElementById("myBtn").innerHTML == "Save") {
        addItem();
    } else {
        updateItem();
    }
}
function addItem() {
    console.log("save()");
    var newitem = {
        id: 0,
        title: document.getElementById("title").value,
        author: document.getElementById("author").value,
        price: document.getElementById("price").value
    };
    console.log(newitem);
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:9090/books", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(newitem));
}
function editItem(id)
{
    document.getElementById("myBtn").innerHTML = "Update";
    const item = books.find((item) => item.id === id);
    document.getElementById("title").value = item.title;
    document.getElementById("author").value = item.author;
    document.getElementById("price").value = item.price;
    updateIndex = id;
    console.log(updateIndex);
    console.log(item.title);
}
function updateItem() {
    console.log("update()");
    var updateitem = {
        id: updateIndex,
        title: document.getElementById("title").value,
        author: document.getElementById("author").value,
        price: document.getElementById("price").value
    };
    console.log(updateitem);
    var xhttp = new XMLHttpRequest();
    xhttp.open("PUT", "http://localhost:9090/books", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(updateitem));
    updateIndex=0;
}
function deleteItem(id) { 
    console.log("delete()");
    var deleteitem = {
        id: id,
        title: "",
        author: "",
        price: 0
    };
    console.log(deleteitem);
    var xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "http://localhost:9090/books", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(deleteitem));
}