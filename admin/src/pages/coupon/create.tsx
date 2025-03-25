import { useForm, Create } from "@refinedev/antd";
import { useSelect } from "@refinedev/core";

import { DatePicker, Form, Input, Select } from "antd";

export const CreateCoupon= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  // useSelect

  return (
    <Create saveButtonProps={saveButtonProps}>
      <Form {...formProps} layout="vertical">
        <Form.Item label="Code" name="code">
          <Input />
        </Form.Item>
        <Form.Item label="Discount amount" name="discount_amount">
          <Input />
        </Form.Item>
        <Form.Item label="Usage Limit" name="usage_limit">
          <Input />
        </Form.Item>
        <Form.Item label="Start Date" name="start_date">
          <DatePicker />
        </Form.Item>
        <Form.Item label="End Date" name="end_date">
          <DatePicker />
        </Form.Item>
        <Form.Item label="Status" name="status">
          <Select options={
              Object.keys(CouponStats).map(key => {return {value: key, label: key}})
            }
          />
        </Form.Item>
      </Form>
    </Create>
  );
};

enum CouponStats {
  DISABLE = 'DISABLE',
  ACTIVE = 'ACTIVE',
  EXPIRE = 'EXPIRE',
}

