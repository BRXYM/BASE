
export interface Admin {
  adm_id?: number;
  adm_name: string;
  adm_phone: string;
  adm_psword: string;
}

export interface Ads {
  ads_id?: number;
  ads_user_id: number;
  ads_txt: string;
}

export interface Good {
  good_id?: number;
  good_sell_id: number;
  good_name: string;
  good_filepath:string;
  good_num: number;
  good_txt: string;
}

export interface Mess {
  mess_id?: number;
  mess_txt: string;
  order_id: number;
}

export interface Order {
  order_id?: number;
  order_good_id: number;
  order_user_id: number;
  order_time?: Date;
}

export interface Sell {
  sell_id?: number;
  sell_phone: string;
  sell_psword: string;
  sell_name: string;
}

export interface User {
  user_id?: number;
  user_phone: string;
  user_psword: string;
  user_name: string;
  user_ads_id: number;
}
