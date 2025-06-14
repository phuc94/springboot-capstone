import { AppShell } from '@mantine/core';
import Header from './Header';
import React from 'react';
import Footer from './Footer';
import axios from 'axios';
import { useAuthStore } from '@/store/useAuthStore';

function Layout({children}: {children: React.ReactNode}) {

// const { isAuthenticated, token } = useAuthStore()
// if (isAuthenticated) {
//   console.log('tokennnnnnnnnnnnn')
//   axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
//   axios.defaults.withCredentials = true
// }


  return (
    <AppShell
      header={{ height: 125 }}
    >
      <AppShell.Header>
        <Header />
      </AppShell.Header>

      <AppShell.Main>{children}</AppShell.Main>

      <Footer />

    </AppShell>
  )
}

export default Layout;
