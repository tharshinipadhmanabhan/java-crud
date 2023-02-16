<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="index.js"></script>
</head>
<body onload="getAll()">
    <h1>index.html</h1>
    <!-- <form action="/books" method="post"> -->
        <input type="text" name="title" id="title">
        <input type="text" name="author" id="author">
        <input type="text" name="price" id="price">
        <button id="myBtn" onclick="saveORupdateItem()">Save</button>
    <!-- </form> -->
    <hr>
    <p id="box"></p>
    <table>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Author</th>
            <th>Price</th>
            <th>Edit</th>
            <th>delete</th>
        </tr>
        <tbody id="books"></tbody>
    </table>
</body>
</html>











<!-- <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="index.js"></script>
</head>
<body onload="getAll()">
    <h1>index.html</h1>
    <form action="/Book" method="post">
        <input type="text" name="title" id="Id">
        <input type="text" name="title" id="title">
        <input type="text" name="author" id="author">
        <input type="text" name="price" id="price">
        <button id="myBtn" onclick="saveORupdateItem()">Save</button>
        <!-- <button onclick="Insert()">Insert</button>
        <button onclick="Update()">Update</button>
        <button onclick="Edit()">Edit</button>
        <button onclick="Delete()">Delete</button> -->
    <!-- </form>
    <hr>
    <p id="Box"></p>
    <table>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Author</th>
            <th>Price</th>
            <th>Edit</th>
            <th>delete</th>
        </tr>
        <tbody id="Books"></tbody>
    </table>
</body> -->
    
    <!-- <a href="/new">Login</a>
    <br>
    <br>
    <button onclick="Getall()">GetAll</button>
    <br>
    <br>
    <button onclick="Insert()">Insert</button> -->
    <!-- <button onclick="update()">update</button>
    <button onclick="DELETE()">delete</button> -->
<!-- </body>
</html> --> 