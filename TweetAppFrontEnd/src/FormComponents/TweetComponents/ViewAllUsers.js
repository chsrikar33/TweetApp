import React from 'react'
const ViewAllUsers = (props) => {
    const users=props.usersList;
    const listItems = users.map((users) =>
    <li key={users.toString()}>
        {users}
    </li>
  );

  return (
    <div>
   <ul>{listItems}</ul>
  </div>
  );
}

export default ViewAllUsers

