import { Authenticated, Refine } from "@refinedev/core";

import { ThemedLayoutV2, ThemedTitleV2, useNotificationProvider } from "@refinedev/antd";
import "@refinedev/antd/dist/reset.css";
import routerProvider, { NavigateToResource } from "@refinedev/react-router";

import { DevtoolsPanel, DevtoolsProvider } from "@refinedev/devtools";
import { RefineKbar, RefineKbarProvider } from "@refinedev/kbar";
import { ColorModeContextProvider } from "./contexts/color-mode";

import { dataProvider } from "./providers/dataProvider";
import { BrowserRouter, Outlet, Route, Routes } from "react-router";
import { authProvider } from "./providers/authProvider";
import { Login } from "./pages/login";
import { ConfigProvider, App as AntdApp } from "antd";
import { CreateProduct, EditProduct, ListProducts, ShowProduct } from "./pages/products";
import { CreateDescription, EditDescription, ListDescription, ShowDescription } from "./pages/description";

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
              // {
              //   name: "protected-products",
              //   list: "/products",
              //   show: "/products/:id",
              //   edit: "/products/:id/edit",
              //   create: "/products/create",
              //   meta: { label: "Products" },
              // },
              {
                name: "game_description",
                list: "/description",
                show: "/description/:id",
                edit: "/description/:id/edit",
                create: "/description/create",
                meta: { label: "Game Description" },
              },
            ]}
          >
            <Routes>
              <Route
                element={
                  <Authenticated key="authenticated-routes" redirectOnFail="/login" >
                    <ThemedLayoutV2
                      Title={(props) =>(
                        <ThemedTitleV2 {...props} text="Springboot Capstone" />
                      )}
                    >
                      <Outlet />
                    </ThemedLayoutV2>
                  </Authenticated>
                }
              >
                <Route
                  index
                  element={<NavigateToResource resource="protected-products" />}
                />
                {/* <Route path="/products">
                  <Route index element={<ListProducts />} />
                  <Route path=":id" element={<ShowProduct />} />
                  <Route path=":id/edit" element={<EditProduct />} />
                  <Route path="create" element={<CreateProduct />} />
                </Route> */}
                <Route path="/description">
                  <Route index element={<ListDescription />} />
                  <Route path=":id" element={<ShowDescription/>} />
                  <Route path=":id/edit" element={<EditDescription/>} />
                  <Route path="create" element={<CreateDescription/>} />
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
