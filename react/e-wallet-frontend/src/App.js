import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Transactions from './components/Transactions';
import User from './components/User';
import Wallet from './components/Wallet';
import { Container } from '@mui/material';
import UserRegister from './components/UserRegister';
import UserLogin from './components/UserLogin';

const App = () => {
  return (
    <Router>
      <Container>
        {/* 使用 Routes 而不是 Switch，每个 Route 组件代表一个页面 */}
        <Routes>
          <Route path="/transactions" element={<Transactions />} />
          <Route path="/users" element={<User />} />
          <Route path="/wallets" element={<Wallet />} />
          <Route path="/" element={<UserLogin/>} />
          {/* 添加一个默认路由或者 404 页面 */}
          {/* <Route path="/" element={<h1>Welcome to the E-Wallet Frontend!</h1>} /> */}
        </Routes>
      </Container>
    </Router>
  );
};

export default App;