// src/components/UserRegister.js

import React, { useState } from 'react';
import { registerUser } from '../api/api';
import { Container, TextField, Button } from '@mui/material';

const UserRegister = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleRegister = async () => {
    try {
      const response = await registerUser({ username, password });
      alert('Registration successful: ' + response.data.username);
    } catch (error) {
      alert('Registration failed: ' + error.response.data);
    }
  };

  return (
    <Container>
      <h2>User Registration</h2>
      <TextField label="Username" value={username} onChange={e => setUsername(e.target.value)} />
      <TextField label="Password" type="password" value={password} onChange={e => setPassword(e.target.value)} />
      <Button onClick={handleRegister}>Register</Button>
    </Container>
  );
};

export default UserRegister;
