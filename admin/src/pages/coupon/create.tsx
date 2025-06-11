import { useForm, Create, useSelect } from "@refinedev/antd";

import { DatePicker, Form, Input, Select } from "antd";

export const CreateCoupon= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  const {selectProps} = useSelect({
    resource: "coupon_type",
    optionLabel: "type"
  })

  // TODO usedCount < usageLimit

  return (
    <Create saveButtonProps={saveButtonProps}>
      <Form {...formProps} layout="vertical">
        <Form.Item label="Code" name="code" required>
          <Input />
        </Form.Item>
        <Form.Item label="Discount amount" name="discountAmount">
          <Input />
        </Form.Item>
        <Form.Item label="Usage Limit" name="usageLimit">
          <Input />
        </Form.Item>
        <Form.Item label="Used Count" name="usedCount">
          <Input required />
        </Form.Item>
        <Form.Item label="Start Date" name="startDate">
          <DatePicker />
        </Form.Item>
        <Form.Item label="End Date" name="endDate">
          <DatePicker />
        </Form.Item>
        <Form.Item label="Status" name="status" required>
          <Select options={
              Object.keys(CouponStatus).map(key => {return {value: key, label: key}})
            }
          />
        </Form.Item>
        <Form.Item label="Coupon Type" name="couponTypeId" required>
          <Select {...selectProps} />
        </Form.Item>
      </Form>
    </Create>
  );
};

export enum CouponStatus {
  DISABLED = 'DISABLED',
  ACTIVE = 'ACTIVE',
  EXPIRE = 'EXPIRE',
}

