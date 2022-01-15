(async function () {
    const res = await fetch('http://localhost:8080/admin/user')
    const user = await res.json()
    const userInfo = document.getElementById("body-user")
    let data = `<tr>
        <td>${user.id}</td>
        <td>${user.userName}</td>
        <td>${user.lastName}</td>
        <td>${user.age}</td>
        <td>${user.email}</td>
        <td>${user.roles[0]?.name}</td>
            </tr>`
    userInfo.innerHTML = data
})()