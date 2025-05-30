import { useForm, Edit, useSelect } from "@refinedev/antd";

import { DatePicker, Form, Input, Select, } from "antd";

import { CouponStatus } from "./create";

export const EditCoupon= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  const {selectProps} = useSelect({
    resource: "coupon_type",
    optionLabel: "type"
  })

  return (
    <Edit saveButtonProps={saveButtonProps} >
      <Form {...formProps} layout="vertical">
        <Form.Item label="Code" name="code">
          <Input />
        </Form.Item>
        <Form.Item label="Discount amount" name="discountAmount">
          <Input />
        </Form.Item>
        <Form.Item label="Usage Limit" name="usageLimit">
          <Input />
        </Form.Item>
        <Form.Item label="Start Date" name="startDate">
          <DatePicker />
        </Form.Item>
      <Form.Item label="End Date" name="endDate">
          <DatePicker />
        </Form.Item>
        <Form.Item label="Status" name="status">
          <Select options={
              Object.keys(CouponStatus).map(key => {return {value: key, label: key}})
            }
          />
        </Form.Item>
        <Form.Item label="Coupon Type" name="couponTypeId">
          <Select {...selectProps} />
        </Form.Item>
      </Form>
    </Edit>
  );
};

