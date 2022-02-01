import Vue from 'vue';
import Router from 'vue-router';
//import DashboardLayout from '../layout/starter/SampleLayout.vue';
import Starter from '../layout/LoginLayout.vue';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      redirect: '/signin',
      component: LoginLayout,
      children: [
        {
          path: 'signin',
          name: 'signin',
          components: { default: Starter }
        }
      ]
    }
  ]
});
