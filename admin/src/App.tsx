import { Authenticated, GitHubBanner, Refine, WelcomePage } from "@refinedev/core";
import { DevtoolsPanel, DevtoolsProvider } from "@refinedev/devtools";
import { RefineKbar, RefineKbarProvider } from "@refinedev/kbar";

import { ThemedLayoutV2, ThemedTitleV2, useNotificationProvider } from "@refinedev/antd";
import "@refinedev/antd/dist/reset.css";
import routerProvider, { NavigateToResource } from "@refinedev/react-router";

import routerBindings, {
  DocumentTitleHandler,
  UnsavedChangesNotifier,
} from "@refinedev/react-router";
import { dataProvider } from "./providers/dataProvider";
import { BrowserRouter, Navigate, Outlet, redirect, Route, Routes } from "react-router";
import { ColorModeContextProvider } from "./contexts/color-mode";
import { ShowProduct } from "./pages/products/show";
import { EditProduct } from "./pages/products/edit";
import { ListProducts } from "./pages/products/list";
import { CreateProduct } from "./pages/products/create";
import { authProvider } from "./providers/authProvider";
import { Login } from "./pages/login";
import { Header } from "./components";
import { ConfigProvider, App as AntdApp } from "antd";

function App() {
  return (
    <BrowserRouter>
      <ConfigProvider>
        <AntdApp>
          <Refine
            dataProvider={dataProvider}
            authProvider={authProvider}
            routerProvider={routerProvider}
            notificationProvider={useNotificationProvider}
            resources={[
              {
                name: "protected-products",
                list: "/products",
                show: "/products/:id",
                edit: "/products/:id/edit",
                create: "/products/create",
                meta: { label: "Products" },
              },
            ]}
          >
            <Routes>
              <Route
                element={
                  <Authenticated key="authenticated-routes" redirectOnFail="/login" >
                    <ThemedLayoutV2
                      Title={(props) =>(
                        <ThemedTitleV2 {...props} text="Awesome Project" />
                      )}
                    >
                      <Outlet />
                    </ThemedLayoutV2>
                  </Authenticated>
                }
              >
                <Route
                  // We're also replacing the <Navigate /> component with the <NavigateToResource /> component.
                  // It's tailored version of the <Navigate /> component that will redirect to the resource's list route.
                  index
                  element={<NavigateToResource resource="protected-products" />}
                />
                <Route path="/products">
                  <Route index element={<ListProducts />} />
                  <Route path=":id" element={<ShowProduct />} />
                  <Route path=":id/edit" element={<EditProduct />} />
                  <Route path="create" element={<CreateProduct />} />
                </Route>
              </Route>
              <Route
                element={
                  <Authenticated key="auth-pages" fallback={<Outlet/>} >
                    <NavigateToResource resource="protected-products" />
                  </Authenticated>
                }
              >
                <Route path="login" element={<Login />} />
              </Route>
            </Routes>
          </Refine>
        </AntdApp>
      </ConfigProvider>
    </BrowserRouter>
  );
}
                // <Header />
                // {/* <ShowProduct /> */}
                // {/* <EditProduct /> */}
                // <ListProducts />
                // {/* <CreateProduct /> */}

export default App;

// function App() {
//   return (
//     <BrowserRouter>
//       <GitHubBanner />
//       <RefineKbarProvider>
//         <ColorModeContextProvider>
//           <AntdApp>
//             <DevtoolsProvider>
//               <Refine
//                 dataProvider={dataProvider}
//                 notificationProvider={useNotificationProvider}
//                 routerProvider={routerBindings}
//                 options={{
//                   syncWithLocation: true,
//                   warnWhenUnsavedChanges: true,
//                   useNewQueryKeys: true,
//                   projectId: "hEjsaq-KVBZPQ-aWaiUP",
//                 }}
//               >
//                 <ShowProduct />
//               </Refine>
//               <DevtoolsPanel />
//             </DevtoolsProvider>
//           </AntdApp>
//         </ColorModeContextProvider>
//       </RefineKbarProvider>
//     </BrowserRouter>
//   );
// }
