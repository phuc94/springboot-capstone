import { useOne, useSelect, useShow } from "@refinedev/core";
import { TextField, MarkdownField, Show, DateField } from "@refinedev/antd";

import { Card, Input, Space, Typography } from "antd";
import dayjs from "dayjs";

export const ShowGame = () => {
  const {
    query: { data, isLoading },
  } = useShow();

  const {data: saleData} = useOne({
    resource: "sale",
    id: data?.data?.saleId
  })

  return (
    <Show isLoading={isLoading}>
      <Typography.Title level={5}>Id</Typography.Title>
      <TextField value={data?.data?.id} />

      <Typography.Title level={5}>Description</Typography.Title>
      <MarkdownField value={data?.data?.description} />

      <Typography.Title level={5}>Sale</Typography.Title>
      {
        saleData ?
        <Card size="small" title="Sale info" >
          <Space size="middle" direction="vertical">
            <Space>
              <Typography.Text>Amount: {saleData.data.amount}%</Typography.Text>
            </Space>
            <Space>
              <Typography.Text>Start date: </Typography.Text>
              <DateField value={dayjs(saleData.data.startDate).toString()} />
            </Space>
            <Space>
              <Typography.Text>End date: </Typography.Text>
              <DateField value={dayjs(saleData.data.endDate).toString()} />
            </Space>
          </Space>
        </Card>
        : null
      }
    </Show>
  );
};
