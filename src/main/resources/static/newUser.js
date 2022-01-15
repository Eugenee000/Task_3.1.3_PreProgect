const linkNewUser = "http://localhost:8080/admin/create";

let userFormNew = {
    userName: "testing",
    lastName: "1",
    age: 1,
    email: "1",
    password: "1",
    roles: [
        {
            name: "ROLE_ADMIN"
        }
    ]
}

async function newUser() {
    try {
        await fetch(linkNewUser, {
            method: "POST",
            body: JSON.stringify(userFormNew),
            headers: {
                "Content-Type": "application/json"
            }
        });
    } catch (e) {
        console.error(e);
    }
}

newbtn.onclick = function () {
    userFormNew.userName = $('#new-username').val();
    userFormNew.lastName = $('#new-lastname').val();
    userFormNew.age = $('#new-age').val();
    userFormNew.email = $('#new-email').val();
    userFormNew.password = $('#new-password').val();
    userFormNew.roles = $('#new-roles').val();
    newUser().then(allUsers);
}