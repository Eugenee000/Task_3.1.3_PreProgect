const linkGetAllUsers = "http://localhost:8080/admin/users";

allUsers();

async function allUsers() {
    console.log("Executing request!");
    try {
        const response = await fetch(linkGetAllUsers);
        const users = await response.json();

        if (users.length > 0) {
            let temp = "";
            users.forEach((u) => {
                temp += "<tr><td>" + u.id + "</td>";
                temp += "<td>" + u.userName + "</td>";
                temp += "<td>" + u.lastName + "</td>";
                temp += "<td>" + u.age + "</td>";
                temp += "<td>" + u.email + "</td>";
                temp += "<td>" + printRoles(u.roles)+ "</td>";
                temp += "<td>" + `<button onclick='updateUserForm(${u.id})' type='button' class='btn btn-info'>Edit</button>` + "</td>";
                temp += `<td><button onclick="deletedUser(${u.id})" class=\"btn btn-danger delBtn\">Delete</button></td></tr>`;
            });
            document.getElementById("allUsers").innerHTML = temp;
        }
    } catch (e) {
        console.error(e);
    }
}

function printRoles(roles) {
    let print = "";
    for (let i = 0; i < roles.length; i++) {
        print += roles[i].name;
        if (roles.length > 1 && i !== 1) {
            print += ", ";
        }
    }
    return print;
}