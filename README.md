**API**:

The Users table is automatically created, 
check your login and password settings in hibernate.properties. 

• Add a new user. **POST**

By this request we are transmitting user data to our Server and save it in the database. The unique ID of the new user is Server response.
We can use only required fields({"name":"newName", "email":"newEmail"}), other fields will have NULL values.
<br>
example of full JSON request:<br>
{<br>
    "name": "Artem",<br>
    "email": "artem@gmail.com",<br>
    "status": "Online",<br>
    "time": "19:01:2018 10:31:10"<br>
}<br>


• Get user from DB. **GET** (…/users/{id})

When we use the unique ID of user from our DB and make this request - server will send user data info by response.


• Change the status of the user (Online, Away, Offline). **PUT** (.../users/{id}/{status})

By this request we are transmitting a unique user ID, new status and change user status in DB. Unique user ID, new and previous status is the Server response.
_If we have changed some user status server will put a new "Away" status after 5 minutes, if we have updated the status again and before automatically server changing - update will be with a new countdown after the last modification._


