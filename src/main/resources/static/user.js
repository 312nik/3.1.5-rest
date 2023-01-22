// данные авторизированного  пользователя


getPrincipal();

function getPrincipal() {
   fetch("/api/user")
       .then(res => res.json())
       .then(data => {
          $('#user').append(data.email);

          $('#user-roles').append(data.roleToString);
          let user = `$(
                <tr>
                    <td>${data.id}</td>
                    <td>${data.name}</td>
                    <td>${data.lastName}</td>
                    <td>${data.age}</td>   
                    <td>${data.email}</td>
                    <td>${data.roleToString}</td>
                </tr>)`;
          $('#tbody').append(user);
       })
}





