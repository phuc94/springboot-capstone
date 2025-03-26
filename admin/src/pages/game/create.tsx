import { useForm, Create, useSelect } from "@refinedev/antd";

import { DatePicker, Form, Image, Input, Select, Switch } from "antd";
import { useWatch } from "antd/es/form/Form";
import { useEffect, useState } from "react";
import ReactQuill from "react-quill";
import 'react-quill/dist/quill.snow.css';

export const CreateGame = () => {
  const { formProps, saveButtonProps, form } = useForm({
    redirect: "show",
  });
  
  const { selectProps: developerProps } = useSelect({
    resource: "developer",
    optionValue: "id",
    optionLabel: "name"
  })

  const { selectProps: esrbProps } = useSelect({
    resource: "esrb",
    optionValue: "id",
    optionLabel: "name"
  })

  const { selectProps: publisherProps } = useSelect({
    resource: "publisher",
    optionValue: "id",
    optionLabel: "name"
  })

  const { selectProps: mediaProps } = useSelect({
    resource: "media",
    optionValue: "id",
    optionLabel: "url"
  })

  const media = useWatch('media', form) || []
  const [selectedMedias, setMedias] = useState<any>([]);

  useEffect(() => {
    setMedias(mediaProps.options?.filter(item => media?.id?.includes(item.value)))
  }, [media])

  return (
    <Create saveButtonProps={saveButtonProps}>
      <Form {...formProps} layout="vertical" >
        <Form.Item label="Title" name="title">
          <Input />
        </Form.Item>
        <Form.Item label="Price" name="price">
          <Input />
        </Form.Item>
        <Form.Item label="Description" name="description">
          <ReactQuill theme="snow" />
        </Form.Item>
        <Form.Item label="Release date" name="release_date">
          <DatePicker />
        </Form.Item>
        <Form.Item label="ESRB rating" name={["esrb_rating", "id"]}>
          <Select {...esrbProps} showSearch={false} />
        </Form.Item>
        <Form.Item label="Developer" name={["developer", "id"]}>
          <Select {...developerProps} showSearch={false} />
        </Form.Item>
        <Form.Item label="Publisher" name={["publisher", "id"]}>
          <Select {...publisherProps} showSearch={false} />
        </Form.Item>
        <Form.Item label="Status" name="status">
          <Select
            showSearch={false}
            options={
              Object.keys(GameStatus).map(key => {return {value: key, label: key}})
            }
          />
        </Form.Item>
        <Form.Item label="Medias" name={["media", "id"]}>
          <Select mode="multiple" {...mediaProps} showSearch={false} />
        </Form.Item>
        { selectedMedias?.map((el: any) => (
            <Image
              key={el.title}
              width={200}
              src={el.label}
            />
          ))
        }
        <Form.Item label="Is Deleted" name="is_deleted">
          <Switch />
        </Form.Item>
        <Form.Item label="Is DLC" name="is_dlc">
          <Switch />
        </Form.Item>
      </Form>
    </Create>
  );
};

enum GameStatus {
  RELEASE = 'RELEASE'
}

