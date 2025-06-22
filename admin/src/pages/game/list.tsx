import {
  getDefaultSortOrder,
  useTable,
  List,
  ShowButton,
  EditButton
} from '@refinedev/antd'
import { useMany } from '@refinedev/core';
import { Space, Switch, Table } from 'antd';


export const ListGame = () => {
  const {
    tableProps,
    sorters,
  } = useTable({
    sorters: {initial: [{field: "id", order: "asc"}]},
    syncWithLocation: true,
    pagination: {
      pageSize: 20
    }
  })

  const {data: saleData} = useMany({
    resource: "sale",
    ids: tableProps?.dataSource?.map(game => game.saleId) ?? []
  })

  const {data: platformData} = useMany({
    resource: "platform",
    ids: tableProps?.dataSource?.map(game => game.platformId) ?? []
  })

  return (
    <List>
      <Table {...tableProps} rowKey="id" >
        <Table.Column
          dataIndex="id"
          title="ID"
          // sorter
          // defaultSortOrder={getDefaultSortOrder("id", sorters)}
        />
        <Table.Column dataIndex="title" title="Title" />
        <Table.Column title="Platform"
          render={(_, record)=>(
            <span>{platformData?.data?.find(platform => platform.id == record.platformId)?.title ?? ""}</span>
          )}
        />
        <Table.Column dataIndex="price" title="Price" />
        <Table.Column dataIndex="stock" title="Stock" />
        <Table.Column title="Sale (%)"
          render={(_, record)=>(
            <span>{saleData?.data?.find(sale => sale.id == record.saleId)?.amount ?? ""}</span>
          )}
        />
        <Table.Column title="Deleted"
          render={(_, record)=> (
            <Switch value={!!record.deleted_on} disabled />
          )}
        />
        <Table.Column
          title="Actions"
          render={(_, record) => (
            <Space>
              <ShowButton hideText size="small" recordItemId={record.id} />
              <EditButton hideText size="small" recordItemId={record.id} />
            </Space>
          )}
        />
      </Table>
    </List>
  );
};
