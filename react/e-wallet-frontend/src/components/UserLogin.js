import React, { useState } from 'react';
import { loginUser } from '../api/api';
import { Container, TextField, Button } from '@mui/material';

const UserLogin = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async () => {
    try {
      const response = await loginUser({ username, password });
      // 使用可选链操作符确保 response.data 不是 undefined
      if (response && response.data) {
        alert('Login successful: ' + response.data);
      } else {
        // 如果 response 或 response.data 是 undefined，提示用户登录失败
        alert('Login failed: No data received');
      }
    } catch (error) {
      // 捕获到错误，从 error.response 中获取错误信息
      if (error.response) {
        // 服务器返回了错误响应
        alert('Login failed: ' + error.response.data);
      } else if (error.request) {
        // 服务器没有响应
        alert('Login failed: No response from server');
      } else {
        // 设置请求时发生错误
        alert('Login failed: ' + error.message);
      }
    }
  };

  return (
    <Container>
      <h2>User Login</h2>
      <TextField
        label="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
      <TextField
        label="Password"
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <Button onClick={handleLogin} variant="contained">Login</Button>
    </Container>
  );
};

export default UserLogin;