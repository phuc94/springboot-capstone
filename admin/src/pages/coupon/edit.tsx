import { useForm, Edit, useSelect } from "@refinedev/antd";

import { DatePicker, Form, Input, Select, Spin, } from "antd";

import { CouponStatus } from "./create";
import dayjs from "dayjs";
import { useEffect } from "react";

export const EditCoupon= () => {
  const { formProps, saveButtonProps, form } = useForm({
    redirect: "show",
  });
  useEffect(()=> {
    if (formProps?.initialValues?.startDate) {
      form.setFieldValue("startDate", dayjs(formProps?.initialValues?.startDate))
      form.setFieldValue("endDate", dayjs(formProps?.initialValues?.endDate))
    } else {
      form.setFieldValue("startDate", dayjs())
      form.setFieldValue("endDate", dayjs())
    }
  },[formProps,form])

  const {selectProps} = useSelect({
    resource: "coupon_type",
    optionLabel: "type"
  })
  if (form.getFieldValue("startDate") == undefined) return <Spin />

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
        <Form.Item label="Start Date" name="startDate" >
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

