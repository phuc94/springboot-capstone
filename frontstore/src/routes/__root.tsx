import { Outlet, createRootRoute } from '@tanstack/react-router'
import { TanStackRouterDevtools } from '@tanstack/router-devtools'
import '@mantine/core/styles.css';
import '@mantine/carousel/styles.css';
import { MantineProvider } from '@mantine/core';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import Layout from '@/components/Layout/index';

export const Route = createRootRoute({
  component: RootComponent,
})

export const queryClient = new QueryClient()

function RootComponent() {
  return (
    <MantineProvider forceColorScheme='dark' >
      <QueryClientProvider client={queryClient} >
        <Layout>
          <Outlet />
        </Layout>
        <TanStackRouterDevtools position="bottom-right" />
        <ReactQueryDevtools position="bottom" />
      </QueryClientProvider >
    </MantineProvider>
  )
}


