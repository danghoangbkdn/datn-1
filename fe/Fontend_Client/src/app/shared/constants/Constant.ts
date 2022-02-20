import { environment } from 'src/environments/environment';

export class Constant {
  public static API_URL = environment.backendUrl + '/api';

  public static LOGIN_URL = [Constant.API_URL, 'auth', 'login'].join('/');

  public static REGISTER_URL = [Constant.API_URL, 'auth', 'sign-up'].join('/');

  public static ADMIN_PRODUCT_URL = [Constant.API_URL, 'admin', 'books'].join('/');

  public static ADMIN_USER_URL = [Constant.API_URL, 'admin',  'users'].join('/');

  public static PRODUCT_URL = [Constant.API_URL, 'products'].join('/');

  public static USER_URL = [Constant.API_URL, 'users'].join('/');

  public static USER_ADDRESS_URL = [Constant.API_URL, 'user-address'].join('/');

  public static COMMENT_URL = [Constant.API_URL, 'comments'].join('/');

}
