import { Outlet, createRootRoute } from '@tanstack/react-router'
import { TanStackRouterDevtools } from '@tanstack/router-devtools'
import { theme } from '../theme';
import '@mantine/core/styles.css';
import { MantineProvider } from '@mantine/core';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';

export const Route = createRootRoute({
  component: RootComponent,
})

function RootComponent() {
const queryClient = new QueryClient()
  return (
    <MantineProvider theme={theme}>
      <QueryClientProvider client={queryClient} >
        <Outlet />
        <TanStackRouterDevtools position="bottom-right" />
        <ReactQueryDevtools position="bottom" />
      </QueryClientProvider >
    </MantineProvider>
  )
}


