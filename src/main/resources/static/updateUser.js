const linkGetUser = "http://localhost:8080/admin/user/";
const linkUpdateUser = "http://localhost:8080/admin/update/";

let userForm = {
    id: 2,
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

async function updateUserForm(id) {
    $(".editUser #exampleModal").modal();

    let user = await (await fetch(linkGetUser + id)).json();

    $('#id').val(user.id);
    $('#edit-username').val(user.userName);
    $('#edit-lastname').val(user.lastName);
    $('#edit-age').val(user.age);
    $('#edit-email').val(user.email);
    $('#edit-password').val(user.password);
    $('#edit-roles').val(user.roles);

    upbtn.onclick = function (e) {
        e.preventDefault()
        console.log(e)
        userForm.id = id;
        userForm.userName = $('#edit-username').val();
        userForm.lastName = $('#edit-lastname').val();
        userForm.age = $('#edit-age').val();
        userForm.email = $('#edit-email').val();
        userForm.password = $('#edit-password').val();
        userForm.roles = $('#edit-roles').val();
        updateUser(id);
    }
}


async function updateUser(id) {
    try {
        const data = await fetch(linkUpdateUser + id, {
            method: "PUT",
            body: JSON.stringify(userForm),
            headers: {
                "Content-Type": "application/json"
            }
        });
        console.log(data)
    } catch (e) {
        console.error(e);
    }
}