import { useForm, Edit, useSelect, DateField } from "@refinedev/antd";

import { Card, Form, Input, Select, Space, Typography } from "antd";
import { useWatch } from "antd/es/form/Form";
import dayjs from "dayjs";
import { useEffect, useState } from "react";
import ReactQuill from "react-quill";

export const EditGame= () => {
  const { formProps, saveButtonProps, form } = useForm({
    redirect: "show",
  });
  const saleId = useWatch('saleId', form) || {}
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  const [saleInfo, setSaleInfo] = useState<any>({
    amount: 0,
    startDate: 0,
    endDate: 0
  });

  useEffect(()=> {
    if (typeof saleId == 'object' && Object.keys(saleId).length === 0) return
    const sale = saleQuery?.query?.data?.data?.find(
      (sale: Sale) => sale.id == form.getFieldValue('saleId')
    )
    setSaleInfo({
      amount: sale?.amount,
      startDate: sale?.startDate,
      endDate: sale?.endDate
    });
  }, [saleId, form])
    
  const {selectProps: platformProps} = useSelect({
    resource: 'platform',
    optionLabel: 'title',
    optionValue: 'id',
    filters: [
      {
        field: "isOrphan",
        operator: "eq",
        value: false,
      },
    ],
  })

  const saleQuery = useSelect({
    resource: 'sale',
    optionLabel: 'amount',
    optionValue: 'id',
  })

  return (
    <Edit saveButtonProps={saveButtonProps} >
      <Form {...formProps} layout="vertical">
        <Form.Item label="Title" name="title">
          <Input />
        </Form.Item>
        <Form.Item label="Price" name="price">
          <Input />
        </Form.Item>
        <Form.Item label="Description" name="description">
          <ReactQuill theme="snow" />
        </Form.Item>
        <Form.Item label="Thumb Image" name="media">
          <Input />
        </Form.Item>
        <Form.Item label="Platform" name="platformId">
          <Select  {...platformProps} showSearch={false} />
        </Form.Item>
        <Form.Item label="Sale" name="saleId">
          <Select  {...saleQuery.selectProps} showSearch={false} />
        </Form.Item>
        <Card size="small" title="Sale info" >
          <Space size="middle" direction="vertical">
            <Space>
              <Typography.Text>Amount: {saleInfo.amount}%</Typography.Text>
            </Space>
            <Space>
              <Typography.Text>Start date: </Typography.Text>
              <DateField value={dayjs(saleInfo.startDate).toString()} />
            </Space>
            <Space>
              <Typography.Text>End date: </Typography.Text>
              <DateField value={dayjs(saleInfo.endDate).toString()} />
            </Space>
          </Space>
        </Card>
        <Form.Item label="Stock" name="stock">
          <Input />
        </Form.Item>
      </Form>
    </Edit>
  );
};

interface Sale {
  amount: number;
  startDate: number;
  endDate: number
}

