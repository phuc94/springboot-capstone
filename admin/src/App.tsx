import { Authenticated, Refine } from "@refinedev/core";

import { ThemedLayoutV2, ThemedTitleV2, useNotificationProvider } from "@refinedev/antd";
import "@refinedev/antd/dist/reset.css";
import routerProvider, { NavigateToResource } from "@refinedev/react-router";

import { dataProvider } from "./providers/dataProvider";
import { BrowserRouter, Outlet, Route, Routes } from "react-router";
import { authProvider } from "./providers/authProvider";
import { Login } from "./pages/login";
import { ConfigProvider, App as AntdApp } from "antd";
import { CreateSale, EditSale, ListSale, ShowSale } from "./pages/sale";
import { CreateCoupon, EditCoupon, ListCoupon, ShowCoupon } from "./pages/coupon";
import { CreateCouponType, EditCouponType, ListCouponType, ShowCouponType } from "./pages/coupon_type";
import { CreateGame, EditGame, ListGame, ShowGame } from "./pages/game";
import { CreatePlatform, EditPlatform, ListPlatform, ShowPlatform } from "./pages/platform";

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
                name: "game",
                list: "/game",
                show: "/game/:id",
                edit: "/game/:id/edit",
                create: "/game/create",
                meta: { label: "Game" },
              },
              {
                name: "sale",
                list: "/sale",
                show: "/sale/:id",
                edit: "/sale/:id/edit",
                create: "/sale/create",
                meta: { label: "Sale" },
              },
              {
                name: "coupon",
                list: "/coupon",
                show: "/coupon/:id",
                edit: "/coupon/:id/edit",
                create: "/coupon/create",
                meta: { label: "Coupon" },
              },
              // {
              //   name: "coupon_type",
              //   list: "/coupon_type",
              //   show: "/coupon_type/:id",
              //   edit: "/coupon_type/:id/edit",
              //   create: "/coupon_type/create",
              //   meta: { label: "Coupon Type" },
              // },
              {
                name: "platform",
                list: "/platform",
                show: "/platform/:id",
                edit: "/platform/:id/edit",
                create: "/platform/create",
                meta: { label: "Platform" },
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
                <Route path="/sale">
                  <Route index element={<ListSale />} />
                  <Route path=":id" element={<ShowSale/>} />
                  <Route path=":id/edit" element={<EditSale/>} />
                  <Route path="create" element={<CreateSale/>} />
                </Route>
                <Route path="/coupon">
                  <Route index element={<ListCoupon />} />
                  <Route path=":id" element={<ShowCoupon/>} />
                  <Route path=":id/edit" element={<EditCoupon/>} />
                  <Route path="create" element={<CreateCoupon/>} />
                </Route>
                {/* <Route path="/coupon_type"> */}
                {/*   <Route index element={<ListCouponType />} /> */}
                {/*   <Route path=":id" element={<ShowCouponType />} /> */}
                {/*   <Route path=":id/edit" element={<EditCouponType />} /> */}
                {/*   <Route path="create" element={<CreateCouponType />} /> */}
                {/* </Route> */}
                <Route path="/game">
                  <Route index element={<ListGame />} />
                  <Route path=":id" element={<ShowGame />} />
                  <Route path=":id/edit" element={<EditGame />} />
                  <Route path="create" element={<CreateGame />} />
                </Route>
                <Route path="/platform">
                  <Route index element={<ListPlatform />} />
                  <Route path=":id" element={<ShowPlatform />} />
                  <Route path=":id/edit" element={<EditPlatform />} />
                  <Route path="create" element={<CreatePlatform />} />
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

