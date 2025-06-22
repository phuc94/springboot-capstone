import { AppShell } from '@mantine/core';
import Header from './Header';
import React from 'react';
import Footer from './Footer';
import { useCart } from '@/hooks/useCart';
import { useCartStore } from '@/store/useCartStore';
import { Notifications } from '@mantine/notifications';

function Layout({children}: {children: React.ReactNode}) {
  const {data, isSuccess} = useCart()
  const setStore = useCartStore(state => state.setStore)
  if (isSuccess) {
    setStore(data?.data)
  }

  return (
    <AppShell
      header={{ height: 125 }}
    >
      <AppShell.Header>
        <Header />
      </AppShell.Header>

      <AppShell.Main>{children}</AppShell.Main>

      <Footer />
      <Notifications position="top-center" />
    </AppShell>
  )
}

export default Layout;
