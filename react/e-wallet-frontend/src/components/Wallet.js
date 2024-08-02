import React, { useState, useEffect } from 'react';
import { getWallets, createWallet, deposit, withdraw, transferMoney } from '../api/api';
import { Container, Button, TextField } from '@mui/material';

const Wallet = () => {
  const [wallets, setWallets] = useState([]);
  const [userId, setUserId] = useState('');
  const [amount, setAmount] = useState('');
  const [sourceWalletId, setSourceWalletId] = useState('');
  const [targetWalletId, setTargetWalletId] = useState('');

  useEffect(() => {
    getWallets().then(response => setWallets(response.data));
  }, []);

  const handleCreateWallet = () => {
    createWallet(userId).then(response => setWallets([...wallets, response.data]));
  };

  const handleDeposit = (walletId) => {
    deposit(walletId, amount).then(response => {
      const updatedWallets = wallets.map(wallet => wallet.id === walletId ? response.data : wallet);
      setWallets(updatedWallets);
    });
  };

  const handleWithdraw = (walletId) => {
    withdraw(walletId, amount).then(response => {
      const updatedWallets = wallets.map(wallet => wallet.id === walletId ? response.data : wallet);
      setWallets(updatedWallets);
    });
  };

  const handleTransfer = () => {
    transferMoney(sourceWalletId, targetWalletId, amount).then(() => {
      getWallets().then(response => setWallets(response.data));
    });
  };

  return (
    <Container>
      <h1>Wallets</h1>
      <TextField label="User ID" value={userId} onChange={e => setUserId(e.target.value)} />
      <Button onClick={handleCreateWallet}>Create Wallet</Button>
      <TextField label="Amount" value={amount} onChange={e => setAmount(e.target.value)} />
      <TextField label="Source Wallet ID" value={sourceWalletId} onChange={e => setSourceWalletId(e.target.value)} />
      <TextField label="Target Wallet ID" value={targetWalletId} onChange={e => setTargetWalletId(e.target.value)} />
      <Button onClick={handleTransfer}>Transfer Money</Button>
      <ul>
        {wallets.map(wallet => (
          <li key={wallet.id}>
            {wallet.id} - {wallet.balance}
            <Button onClick={() => handleDeposit(wallet.id)}>Deposit</Button>
            <Button onClick={() => handleWithdraw(wallet.id)}>Withdraw</Button>
          </li>
        ))}
      </ul>
    </Container>
  );
};

export default Wallet;
