// src/api/api.js

import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080'; // 你的后端服务器地址

// Transactions API
export const getTransactions = () => axios.get(`${API_BASE_URL}/transactions`);
export const createTransaction = (transaction) => axios.post(`${API_BASE_URL}/transactions`, transaction);
export const updateTransaction = (id, transaction) => axios.put(`${API_BASE_URL}/transactions/${id}`, transaction);
export const deleteTransaction = (id) => axios.delete(`${API_BASE_URL}/transactions/${id}`);

// Users API
export const getUsers = () => axios.get(`${API_BASE_URL}/users`);
export const getUser = (username) => axios.get(`${API_BASE_URL}/users/${username}`);
export const registerUser = (user) => axios.post(`${API_BASE_URL}/users/register`, user);
export const loginUser = (user) => axios.post(`${API_BASE_URL}/users/login`, user);
export const updateUser = (username, user) => axios.put(`${API_BASE_URL}/users/${username}`, user);
export const deleteUser = (username) => axios.delete(`${API_BASE_URL}/users/${username}`);

// Wallets API
export const getWallets = () => axios.get(`${API_BASE_URL}/wallets`);
export const createWallet = (userId) => axios.post(`${API_BASE_URL}/wallets`, { userId });
export const deposit = (walletId, amount) => axios.post(`${API_BASE_URL}/wallets/${walletId}/deposit`, { amount });
export const withdraw = (walletId, amount) => axios.post(`${API_BASE_URL}/wallets/${walletId}/withdraw`, { amount });
export const transferMoney = (sourceWalletId, targetWalletId, amount) => axios.post(`${API_BASE_URL}/wallets/${sourceWalletId}/transfer/${targetWalletId}`, { amount });
export const updateWallet = (id, wallet) => axios.put(`${API_BASE_URL}/wallets/${id}`, wallet);
export const deleteWallet = (id) => axios.delete(`${API_BASE_URL}/wallets/${id}`);
