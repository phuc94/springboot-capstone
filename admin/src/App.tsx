import { Authenticated, Refine } from "@refinedev/core";

import { ThemedLayoutV2, ThemedTitleV2, useNotificationProvider } from "@refinedev/antd";
import "@refinedev/antd/dist/reset.css";
import routerProvider, { NavigateToResource } from "@refinedev/react-router";

// import { DevtoolsPanel, DevtoolsProvider } from "@refinedev/devtools";
// import { RefineKbar, RefineKbarProvider } from "@refinedev/kbar";
// import { ColorModeContextProvider } from "./contexts/color-mode";

import { dataProvider } from "./providers/dataProvider";
import { BrowserRouter, Outlet, Route, Routes } from "react-router";
import { authProvider } from "./providers/authProvider";
import { Login } from "./pages/login";
import { ConfigProvider, App as AntdApp } from "antd";
import { CreateDescription, EditDescription, ListDescription, ShowDescription } from "./pages/description";
import { CreateDeveloper, EditDeveloper, ListDeveloper, ShowDeveloper } from "./pages/developer";
import { CreateGenre, EditGenre, ListGenre, ShowGenre } from "./pages/genre";
import { CreatePublisher, EditPublisher, ListPublisher, ShowPublisher } from "./pages/publisher";
import { CreateNoPlayer, EditNoPlayer, ListNoPlayer, ShowNoPlayer } from "./pages/no_player";
import { CreatePlaymode, EditPlaymode, ListPlaymode, ShowPlaymode } from "./pages/playmode";
import { CreateSupportLanguage, EditSupportLanguage, ListSupportLanguage, ShowSupportLanguage } from "./pages/support_language";
import { CreateSale, EditSale, ListSale, ShowSale } from "./pages/sale";
import { CreateMedia, EditMedia, ListMedia, ShowMedia } from "./pages/media";
import { CreateCoupon, EditCoupon, ListCoupon, ShowCoupon } from "./pages/coupon";
import { CreateCouponType, EditCouponType, ListCouponType, ShowCouponType } from "./pages/coupon_type";

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
                name: "game_description",
                list: "/description",
                show: "/description/:id",
                edit: "/description/:id/edit",
                create: "/description/create",
                meta: { label: "Game Description" },
              },
              {
                name: "game",
                list: "/game",
                show: "/game/:id",
                edit: "/game/:id/edit",
                create: "/game/create",
                meta: { label: "Game" },
              },
              {
                name: "developer",
                list: "/developer",
                show: "/developer/:id",
                edit: "/developer/:id/edit",
                create: "/developer/create",
                meta: { label: "Developer" },
              },
              {
                name: "genre",
                list: "/genre",
                show: "/genre/:id",
                edit: "/genre/:id/edit",
                create: "/genre/create",
                meta: { label: "Game Genre" },
              },
              {
                name: "publisher",
                list: "/publisher",
                show: "/publisher/:id",
                edit: "/publisher/:id/edit",
                create: "/publisher/create",
                meta: { label: "Publisher" },
              },
              {
                name: "no_player",
                list: "/no_player",
                show: "/no_player/:id",
                edit: "/no_player/:id/edit",
                create: "/no_player/create",
                meta: { label: "Number of Players" },
              },
              {
                name: "playmode",
                list: "/playmode",
                show: "/playmode/:id",
                edit: "/playmode/:id/edit",
                create: "/playmode/create",
                meta: { label: "Play mode" },
              },
              {
                name: "support_language",
                list: "/support_language",
                show: "/support_language/:id",
                edit: "/support_language/:id/edit",
                create: "/support_language/create",
                meta: { label: "Support Language" },
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
                name: "media",
                list: "/media",
                show: "/media/:id",
                edit: "/media/:id/edit",
                create: "/media/create",
                meta: { label: "Media" },
              },
              {
                name: "coupon",
                list: "/coupon",
                show: "/coupon/:id",
                edit: "/coupon/:id/edit",
                create: "/coupon/create",
                meta: { label: "Coupon" },
              },
              {
                name: "coupon_type",
                list: "/coupon_type",
                show: "/coupon_type/:id",
                edit: "/coupon_type/:id/edit",
                create: "/coupon_type/create",
                meta: { label: "Coupon Type" },
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
                <Route path="/developer">
                  <Route index element={<ListDeveloper />} />
                  <Route path=":id" element={<ShowDeveloper/>} />
                  <Route path=":id/edit" element={<EditDeveloper/>} />
                  <Route path="create" element={<CreateDeveloper/>} />
                </Route>
                <Route path="/genre">
                  <Route index element={<ListGenre />} />
                  <Route path=":id" element={<ShowGenre/>} />
                  <Route path=":id/edit" element={<EditGenre/>} />
                  <Route path="create" element={<CreateGenre/>} />
                </Route>
                <Route path="/publisher">
                  <Route index element={<ListPublisher />} />
                  <Route path=":id" element={<ShowPublisher/>} />
                  <Route path=":id/edit" element={<EditPublisher/>} />
                  <Route path="create" element={<CreatePublisher/>} />
                </Route>
                <Route path="/no_player">
                  <Route index element={<ListNoPlayer />} />
                  <Route path=":id" element={<ShowNoPlayer/>} />
                  <Route path=":id/edit" element={<EditNoPlayer/>} />
                  <Route path="create" element={<CreateNoPlayer/>} />
                </Route>
                <Route path="/playmode">
                  <Route index element={<ListPlaymode />} />
                  <Route path=":id" element={<ShowPlaymode/>} />
                  <Route path=":id/edit" element={<EditPlaymode/>} />
                  <Route path="create" element={<CreatePlaymode/>} />
                </Route>
                <Route path="/support_language">
                  <Route index element={<ListSupportLanguage />} />
                  <Route path=":id" element={<ShowSupportLanguage/>} />
                  <Route path=":id/edit" element={<EditSupportLanguage/>} />
                  <Route path="create" element={<CreateSupportLanguage/>} />
                </Route>
                <Route path="/sale">
                  <Route index element={<ListSale />} />
                  <Route path=":id" element={<ShowSale/>} />
                  <Route path=":id/edit" element={<EditSale/>} />
                  <Route path="create" element={<CreateSale/>} />
                </Route>
                <Route path="/media">
                  <Route index element={<ListMedia />} />
                  <Route path=":id" element={<ShowMedia/>} />
                  <Route path=":id/edit" element={<EditMedia/>} />
                  <Route path="create" element={<CreateMedia/>} />
                </Route>
                <Route path="/coupon">
                  <Route index element={<ListCoupon />} />
                  <Route path=":id" element={<ShowCoupon/>} />
                  <Route path=":id/edit" element={<EditCoupon/>} />
                  <Route path="create" element={<CreateCoupon/>} />
                </Route>
                <Route path="/coupon-type">
                  <Route index element={<ListCouponType />} />
                  <Route path=":id" element={<ShowCouponType />} />
                  <Route path=":id/edit" element={<EditCouponType />} />
                  <Route path="create" element={<CreateCouponType />} />
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
