import React, { useState, useEffect } from 'react';
import { getTransactions, createTransaction } from '../api/api';
import { Container, Button, TextField } from '@mui/material';

const Transactions = () => {
  const [transactions, setTransactions] = useState([]);
  const [amount, setAmount] = useState('');

  useEffect(() => {
    getTransactions().then(response => setTransactions(response.data));
  }, []);

  const handleCreateTransaction = () => {
    createTransaction({ amount }).then(response => setTransactions([...transactions, response.data]));
  };

  return (
    <Container>
      <h1>Transactions</h1>
      <TextField label="Amount" value={amount} onChange={e => setAmount(e.target.value)} />
      <Button onClick={handleCreateTransaction}>Create Transaction</Button>
      <ul>
        {transactions.map(transaction => (
          <li key={transaction.id}>{transaction.amount}</li>
        ))}
      </ul>
    </Container>
  );
};

export default Transactions;
