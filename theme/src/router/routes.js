import DashboardLayout from "@/layout/dashboard/DashboardLayout.vue";
import LoginLayout from "@/layout/dashboard/LoginLayout.vue";
// GeneralViews
import NotFound from "@/pages/NotFoundPage.vue";

// Admin pages
const DkpSystem = () => import(/* webpackChunkName: "dashboard" */"@/pages/DkpSystem.vue");
const Profile = () => import(/* webpackChunkName: "common" */ "@/pages/Profile.vue");
const Notifications = () => import(/* webpackChunkName: "common" */"@/pages/Notifications.vue");
const Items = () => import(/* webpackChunkName: "common" */ "@/pages/Items.vue");
const Clans = () => import(/* webpackChunkName: "common" */ "@/pages/Clans.vue");
const Teste = () => import(/* webpackChunkName: "common" */ "@/pages/Teste.vue");
const TableList = () => import(/* webpackChunkName: "common" */ "@/pages/TableList.vue");

const routes = [
  {
    path: "/signin",
    component: LoginLayout,
    name: "Login"
  },
  {
    path: "/",
    component: DashboardLayout,
    redirect: "/dkpsystem",
    children: [
      {
        path: "dkpsystem",
        name: "dkpsystem",
        component: DkpSystem
      },
      {
        path: "profile",
        name: "profile",
        component: Profile
      },
      {
        path: "notifications",
        name: "notifications",
        component: Notifications
      },
      {
        path: "items",
        name: "items",
        component: Items
      },
      {
        path: "clan",
        name: "clans",
        component: Clans
      },
      {
        path: "teste",
        name: "teste",
        component: Teste
      },
      {
        path: "table-list",
        name: "table-list",
        component: TableList
      }
    ]
  },
  { path: "*", component: NotFound },
];

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
function view(name) {
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/

export default routes;
