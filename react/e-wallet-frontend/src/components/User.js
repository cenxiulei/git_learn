import React, { useState, useEffect } from 'react';
import { getUsers, registerUser, loginUser } from '../api/api';
import { Container, Button, TextField } from '@mui/material';

const User = () => {
  const [users, setUsers] = useState([]);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  useEffect(() => {
    getUsers().then(response => setUsers(response.data));
  }, []);

  const handleRegister = () => {
    registerUser({ username, password }).then(response => setUsers([...users, response.data]));
  };

  const handleLogin = () => {
    loginUser({ username, password }).then(response => {
      console.log('JWT Token:', response.data);
    });
  };

  return (
    <Container>
      <h1>Users</h1>
      <TextField label="Username" value={username} onChange={e => setUsername(e.target.value)} />
      <TextField label="Password" type="password" value={password} onChange={e => setPassword(e.target.value)} />
      <Button onClick={handleRegister}>Register</Button>
      <Button onClick={handleLogin}>Login</Button>
      <ul>
        {users.map(user => (
          <li key={user.id}>{user.username}</li>
        ))}
      </ul>
    </Container>
  );
};

export default User;
