// User 用户
export interface User {
    user_id?: number | null; // 用户ID
    user_img?: string | null; // 用户头像URL
    user_name?: string | null; // 用户名
    user_phone?: string | null; // 用户手机号
    user_pass?: string | null; // 用户密码
}

// Ord 订单
export interface Ord {
    ord_id?: number | null; // 订单ID
    good_id?: number | null; // 商品ID
    user_id?: number | null; // 用户ID
    ord_time?: string | null; // 订单时间
    ord_num?: number | null; // 订单数量
}

// Cla 课程
export interface Cla {
    cla_id?: number | null; // 课程ID
    cla_name?: string | null; // 课程名称
    teach_id?: number | null; // 教练ID
    cla_time?: string | null; // 课程时间
}

// Good 商品
export interface Good {
    good_id?: number | null; // 商品ID
    good_name?: string | null; // 商品名称
    good_img?: string | null; // 商品图片URL
    good_txt?: string | null; // 商品描述
    good_price?: number | null; // 商品价格
    good_num?: number | null; // 商品数量
}

// Sela 选课
export interface Sela {
    sela_id?: number | null; // 选课ID
    cla_id?: number | null; // 课程ID
    user_id?: number | null; // 用户ID
    sela_time?: string | null; // 选课时间
}

// Teach 教练
export interface Teach {
    teach_id?: number | null; // 教练ID
    teach_name?: string | null; // 教练姓名
    teach_img?: string | null; // 教练头像URL
    teach_phone?: string | null; // 教练手机号
    teach_pass?: string | null; // 教练密码
    teach_txt?: string | null; // 教师简介
}