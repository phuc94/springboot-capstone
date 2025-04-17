import { AppShell } from '@mantine/core';
import Header from './Header';
import React from 'react';
import Footer from './Footer';

function Layout({children}: {children: React.ReactNode}) {

  return (
    <AppShell
      header={{ height: 110 }}
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
